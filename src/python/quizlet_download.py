import json, requests


def load(unit, quiz_id):
    url = "https://api.quizlet.com/2.0/sets/" + quiz_id + "/terms?client_id=AwMtxjUkB7"
    r = requests.get(url)
    data = json.loads(r.text)

    output = open("../main/resources/com/rowantran/deadwordchecker/vocablists/unit" + unit + ".txt", "w")
    for i, term in enumerate(data):
        if i != len(data)-1:
            output.write(term['term'] + "\n")
        else:
            output.write(term['term'])
    output.close()


quizlet_ids = open("quizlet_ids.txt", "r")
for line in quizlet_ids:
    unit, quiz_id = line.split(" ")
    load(unit, quiz_id)
quizlet_ids.close()