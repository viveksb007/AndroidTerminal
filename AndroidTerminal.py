# from REST API OF http://ozgur.github.io/python-firebase/

# Put yout username in all instances of  < /users/viveksb007  ->  /users/yourusername  >


from firebase import firebase
import subprocess
import time


def execmd(cmd):
    return subprocess.getoutput(cmd)

def postresponse(response):
    r = f.post('/users/viveksb007/response',response)
    print(r)

def readcmd():
    r = f.get('users/viveksb007/cmds',None)
    if r != None:
        values = list(r.values())
        print(values)
        postresponse(execmd(values[0]))
        f.delete('users/viveksb007/cmds',None)

f = firebase.FirebaseApplication("https://androidterminal.firebaseio.com")

while True:
    readcmd()

    time.sleep(0.5)