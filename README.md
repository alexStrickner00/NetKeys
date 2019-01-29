# NetKeys
A Java application to bind key inputs on methods, which allow you to send strings via a Socket.

## How to use
Configure your key bindings in the `keys.conf` file. Following expressions are allowed:
```
#send(<String to send>)
#var(<varName>,<varValue>)

#<KeyChar>=<expression>(<vars>....)
#<expression>(<vars>...)

#e.g:

#if key k is pressed, send String "hello"
k=send(hello)


#if key k is pressed, set variable v to value 4
k=var(v,4)

#initially set variable v to 5
var(v,5)

#send value of variable v on key p pressed
p=send(value of v is: $(v)!)
```
