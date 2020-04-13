Step 1:
Sandwiches:
# Note: First letters of Main ingredients are capitalized
    1. Kim's Veggie Special
        Usual Ingredients: Swiss Cheese and Avocado, with mayo, tomato, lettuce, onion, carrot, and cucumber
    2. BLT
        Usual Ingredients: Bacon, with lettuce and tomato
    3. Meat Lovers
        Usual Ingredients: Roast Beef and Provolone, with lettuce, tomato and mayo
    4. Italian Night Club
        Usual Ingredients: Salami, Capicola, Ham and Provolone, with onion, lettuce, tomato, mayo and oil&vinegar

Choice of bread-type
    1. French (Default)
    2. rye
    3. wheat

Choice of spread:
    mayonnaise (Default)
    oil&vinegar
    mustard

Possible Options:
    Toasted bread
    Salt and Pepper
    Extra [element] (all available ingredients are possible)

Possible Exceptions:
    No [element] (all ingredients in "usual ingredients" are possible)
    Hold [element]
    Without [element]

Step 2:
List of equivalent terms:
[
    ["veg", "veggie", "vegetarian"],
    ["mayo", "mayonnaise"],
    ["italian", "italy"]
]

List of ignored terms:
["please", "I'd like", "I want"]

Allowed ways of exception:
[
    "hold", "no", "without", "throw"
]

Step 3:
Language: Python 3 (Version 3.6.8 64-bit)
Running command: python sandwich.py, then type your order and enter.
chatbot.py is a single file which contains all logics for chatbot

step 4:
Example 1:
    Welcome to Online Sandwich System, What do you want?
    > What's today's menu?
    Welcome! Here's today's menu!
    Choice No.1:
        Kim's Veggie Special
        Ingredients: CHEESE, AVOCADO, with tomato, lettuce, onion, carrot, cucumber,
    Choice No.2:
        BLT
        Ingredients: BACON, with lettuce, tomato,
    Choice No.3:
        Meat Lovers
        Ingredients: BEEF, with provolone, lettuce, tomato, mayo,
    Choice No.4:
        Italian Night Club
        Ingredients: SALAMI, CAPICOLA, HAM, PROVOLONE, with onion, lettuce, tomato, mayo, oil&vinegar,
    > one Kim's veg, please
    Any other requirements?
    > No onion
    Thank you! Here's your order
        Kim's Veggie Special
        Bread: French
        Ingredients: ['Cheese', 'Avocado', 'tomato', 'lettuce', 'carrot', 'cucumber']
        Spread: mayo
    Thank you for your purchase! Goodbye!

Example 2:
    Welcome to Online Sandwich System, What do you want?
    > Italian night club, rye, no mayo or onion
    Any other requirements?
    > extra carrot
    Thank you! Here's your order
        Italian Night Club
        Bread: rye
        Ingredients: ['Salami', 'Capicola', 'Ham', 'Provolone', 'lettuce', 'tomato', 'oil&vinegar']
        Special: ['carrot']
    Thank you for your purchase! Goodbye!

Example 3:
    Welcome to Online Sandwich System, What do you want?
    > give me today's choice?
    Welcome! Here's today's menu!
    Choice No.1:
        Kim's Veggie Special
        Ingredients: CHEESE, AVOCADO, with tomato, lettuce, onion, carrot, cucumber,
    Choice No.2:
        BLT
        Ingredients: BACON, with lettuce, tomato,
    Choice No.3:
        Meat Lovers
        Ingredients: BEEF, with provolone, lettuce, tomato, mayo,
    Choice No.4:
        Italian Night Club
        Ingredients: SALAMI, CAPICOLA, HAM, PROVOLONE, with onion, lettuce, tomato, mayo, oil&vinegar,
    > OK, I'd like one BLT, no tomato, add lettuce
    Any other requirements?
    > no
    Thank you! Here's your order
        BLT
        Bread: French
        Ingredients: ['Bacon', 'lettuce']
        Spread: mayo
        Special: ['lettuce']
    Thank you for your purchase! Goodbye!

Example 4 (Fail Example):
    Welcome to Online Sandwich System, What do you want?
    > Jimmy Johns' best seller, please
    Sorry, I can't understand your order...
    Thank you for your purchase! Goodbye!

Why Fail: The customer does not provide a valid name of available sandwich.

Step 5:
Easiest part: 
    Choose available Sandwiches and construct matching codes
Hardest part: 
    Deal with add-ons and deletes.
    Design the whole message loop and share information.
Learned:
    How to code python to deal with logics. 
