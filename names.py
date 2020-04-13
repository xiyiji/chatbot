chris = {}
chris['first'] = 'Chris'
chris['last'] = 'Harrison'

susan = {}
susan['first'] = 'Susan'
susan['last'] = 'Ibach'

people = []
people.append(susan)
people.append(chris)
people.append({
    'first':'Bill',
    'last':'Gates'
})
print(people)