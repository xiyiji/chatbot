import re
import copy

class Sandwich:
    def __init__(self, idx, const):
        name = const.name[idx]
        self.name = name
        self.bread_type = const.bread_type[0]   # default type
        self.spread_type = const.spread_type[0]          # default type
        self.options = []
        self.ingredients = copy.copy(const.usual_ingredients[idx])


class Constants:
    def __init__(self):
        self.name = ["Kim's Veggie Special", "BLT", "Meat Lovers", "Italian Night Club"]
        self.usual_ingredients = [
            ["Cheese", "Avocado", "tomato", "lettuce", "onion", "carrot", "cucumber"],
            ["Bacon", "lettuce", "tomato"],
            ["Beef", "provolone", "lettuce", "tomato", "mayo"],
            ["Salami", "Capicola", "Ham", "Provolone", "onion", "lettuce", "tomato", "mayo", "oil&vinegar"]
        ]
        self.bread_type = [
            "French", "rye", "wheat"
        ]
        self.spread_type = [
            "mayo", "oil&vinegar", "mustard"
        ]
        # Note: the first element is considered as "main" name of this term
        self.equivalent_terms = {
            "veggie": ["veggie", "veg", "vegetarian"],
            "mayo": ["mayo", "mayonnaise"],
            "italian": ["italian", "italy"]
        }
        self.ignored_terms = ["please", "I'd like", "I want"]
        self.exception_terms = [
            "hold", "no", "without", "throw"
        ]
        self.add_terms = [
            "add", "extra", "more", "double"
        ]
        self.sandwich = None
        self.ask_one_more_flag = False
        self.finish_flag = False

def init_system():
    # Init some dynamic part of constants
    const = Constants()
    equal_dict = {}         # save all equivalent mapping as a dict into Constants class
    for equal_list in const.equivalent_terms.values():
        main_item = equal_list[0]
        for i in range(1, len(equal_list)):
            equal_dict[equal_list[i]] = main_item
    
    for lis in const.usual_ingredients:
        for item in lis:
            if (item not in equal_dict.keys()):
                equal_dict[item] = [item+'s', item+'es']
            else:
                equal_dict[item].extend([item+'s', item+'es'])

    const.equivalent_terms = equal_dict
    return const

def is_question(command):
    if ("?" in command or "what" in command):
        return True
    return False

def is_requesting_menu(command):
    if ("menu" in command or "choice" in command):
        return True
    return False

def construct_description(ingredients):
    # construct a description to show main ingredients with upper_case and add-ons with lower_case
    ret = ""
    count = 0
    main_flag = True
    for item in ingredients:
        if (item[0] >= "A" and item[0] <= "Z"):
            ret += item.upper()
            ret += ", "
        else:
            if (main_flag):
                ret += "with "
                main_flag = False
            ret += item.lower()
            ret += ", "
    return ret

def display_menu(const):
    print("Welcome! Here's today's menu!")
    for i in range(len(const.name)):
        print("Choice No.{0}:".format(i+1))
        print("    {0}".format(const.name[i]))
        print("    Ingredients: {0}".format(construct_description(const.usual_ingredients[i])))

def init_sandwich_by_name(const, main_name_idx):
    const.sandwich = Sandwich(main_name_idx, const)
    return True

def command_to_plain(command, split = False):
    # Function: convert command into plain text
    # example: "Kim’s Veggie please, with no onions" => "kims veggie please with no onions"
    command = command.lower()
    ret_list = []
    ret_temp = ""
    ret = ""
    for char in command:
        if (char >= "a" and char <"z") or (char == " " and len(ret) > 0 and ret[len(ret)-1] != " "):
            ret += char
            ret_temp += char
        elif (char == ","):
            ret += " "
            ret_list.append(ret_temp)
            ret_temp = ""
    ret_list.append(ret_temp)
    if (not split):
        return ret
    else:
        return ret_list

def match_keyword_with_equal(const, command, keyword_list):
    for keyword in keyword_list:
        temp = [keyword]
        if (keyword in const.equivalent_terms.keys()):
            temp.extend(const.equivalent_terms[keyword])
        for item in temp:
            if (command.find(item) != -1):
                return True
    return False

def find_main_name(const, command):
    plain = command_to_plain(command)
    keyword_lists = [
        ["kim", "veggie", "special"],
        ["blt"],
        ["meat", "meatlover", "lover"],
        ["italian", "night", "club"]
    ]
    for i in range(len(keyword_lists)):
        if (match_keyword_with_equal(const, plain, keyword_lists[i])):
            return i
    return -1

def find_ingredients(const, command):
    # find the specific orders in command
    plain = command_to_plain(command)
    plain_list = command_to_plain(command, split = True)
    # print(plain, plain_list)
    ingredient_lists = const.usual_ingredients
    bread_types = const.bread_type
    spread_types = const.spread_type
    valid_ingredients = []
    for l in ingredient_lists:
        temp = []
        for element in l:
            if (element in const.equivalent_terms.keys()):
                temp.extend(const.equivalent_terms[element])
        valid_ingredients.extend(l)
        valid_ingredients.extend(temp)
    # print(valid_ingredients)
    adds, removes = [], []
    remove_flag = False     # flag to indicate whether remove following ingredients

    for plain_part in plain_list:
        words = plain_part.split(" ")
        for word in words:
            if (word in const.exception_terms):
                remove_flag = True
            elif (word in const.add_terms):
                remove_flag = False
            if (word in valid_ingredients):
                if (remove_flag):
                    removes.append(word)
                else:
                    adds.append(word)
            if (word in bread_types):
                adds.append(word)
    check_multiple_form(adds, removes, const)
    return adds, removes

def check_multiple_form(adds, removes, const):
    adds_append, removes_append = [], []
    for key in const.equivalent_terms.keys():
        for item in adds:
            if item in const.equivalent_terms[key]:
                adds_append.append(key)
        for item in removes:
            if item in const.equivalent_terms[key]:
                removes_append.append(key)
    adds.extend(adds_append)
    removes.extend(removes_append)

def personalize(adds, removes, const):
    # print(removes)
    for item in removes:
        if (item == const.sandwich.spread_type):
            const.sandwich.spread_type = ""
        try:
            # print(item)
            # print(const.sandwich.ingredients)
            const.sandwich.ingredients.remove(item)
            # print(const.sandwich.ingredients)
        except:
            pass
    for item in adds:
        if (item in const.bread_type):
            const.sandwich.bread_type = item
        else:
            const.sandwich.options.append(item)
    return

def parse_order(const, command):
    if (const.sandwich == None):
        main_name_idx = find_main_name(const, command)  # get the dish name
        if (main_name_idx != -1):
            init_sandwich_by_name(const, main_name_idx)
        else:
            print("Sorry, I can't understand your order...")
            const.finish_flag = True
    if (const.sandwich != None):
        adds, removes = find_ingredients(const, command)
        # print(adds, removes)
        personalize(adds, removes, const)

def display_order(sandwich):
    if (sandwich != None):
        print("Thank you! Here's your order")
        print("    {0}".format(sandwich.name))
        print("    Bread: {0}".format(sandwich.bread_type))
        print("    Ingredients: {0}".format(sandwich.ingredients))
        if (sandwich.spread_type != ""):
            print("    Spread: {0}".format(sandwich.spread_type))
        if (len(sandwich.options) > 0):
            print("    Special: {0}".format(sandwich.options))

def main_loop(const, command):
    command = command.lower()
    if is_requesting_menu(command):
        display_menu(const)
        return False
    parse_order(const, command)
    if (const.sandwich != None):
        if (const.ask_one_more_flag == False):
            print("Any other requirements?")
            const.ask_one_more_flag = True
        else:
            const.finish_flag = True
            display_order(const.sandwich)


def test(const):
    main_loop(const, "Kim’s Veggie please, rye, with no onions, tomato or asd")
    # example: "Kim’s Veggie please, with no onions" => "kims veggie please with no onions"


if __name__ == "__main__":
    const = init_system()
    # test(const)
    print("Welcome to Online Sandwich System, What do you want?")
    while(True):
        print(" > ", end = "")
        try:
            command = input()
        except:
            break
        main_loop(const, command)
        code = const.finish_flag
        if (code == True):
            break
    print("Thank you for your purchase! Goodbye!")