# Encoder 

## A Java Desktop Application that allows a user to encode a message

*List Of Ciphers Available:*
- English
- Atbash Cipher
- ROT13 Cipher
- Cesar Cipher(shift = 1 to the right)
- Custom Cipher*

*Custom Cipher, is a cipher that is created by a user, and is not available as a default to any other user. 
So any Custom Cipher, is only available on that device itself, unless the user shares it with another user. 
No one can have access to another user's Custom Code, unless shared by that user, also note since 
Encoder does not yet have the capabilities to communicate with other users, it can only convert messages, not send them, 
or receive them. Keep in mind that any cipher key that the user creates, must be less than or equal to 26 characters, 
and if it does not have a length of 26, then Encoder will experience an issue.

Encoder is a Java Desktop Application that allows a user to convert a message from English to any supported language. 
For example, a user could use it to convert a message from English to Atbash Cipher or Atbash Cipher To English. 
Encoder allows the user to create a Custom Code Language along with the available default options, allowing users to 
encode their messages however they want.

Encoder allows anyone to be able to relay their messages securely and offers users complete customizability.
In a time when privacy is not available to everyone, Encoder helps give more people access to some security. 
Encoder could be used anywhere and has limitless possibilities since everything remains on the device; no one can 
intercept a User's message and decrypt it since the key is stored locally on the device. 
Numerous events have shown that hackers are everywhere, and Encoder helps cut down on this problem.
Encoder is useful for everyone; it could be used in places like China, where Governments are strict; 
it helps give users a sense of security. Encoder helps give a user a key to a lock, and no one except anyone the user
shares that key with can open that lock.

Encoder, as a project, is something I honestly would like to use; I have always felt that I had no privacy. 
Encoder helps solve that issue by letting me define my privacy. 
Encoder also interests me, as I have always wanted to be able to communicate using code and cyphers, like the Caesar Cipher. 
However, it just seems too difficult to learn, and Encoder does not have a learning curve. 
It is a simple application that does exactly what its name means; it Encodes and can also Decode a message. 
That's it.

## User Stories
- As a User, I want to be able to create my own cipher, and add it to the list of ciphers/languages available.
- As a User, I want to be able to decrypt a message back into English.
- As a User, I want to be able to encrypt my message into one of the available ciphers/languages.
- As a User, I want to be able to edit a cipher that I created, and delete it.
- As a User, I want to be able to export my cipher into a format that I can share with another user, 
get my custom cipher in a format, that another user can import into their own version of the application.
- As a User, I want to be able to save my List of Ciphers to file.
- As a User, I want to be able to load my List of Cipher from file.

## Phase 4: Task 2
I have chosen to make the Cipher class in my model package robust, and the two methods that help acomplish that goal 
are, the encrypt() and decrypt() methods.

## Phase 4: Task 3
If I had more time, one of the first changes I would make, would be probably changing the type of CipherList from 
ArrayList() to something that works better with JList, as I learned that it was quite difficult for me to get my actual
CipherList to display with JList, I had to basically make a new DefaultListModel list, and use it to for the display.
Changing CipherList from ArrayList to DefaultListModel might work better, but its something I would have to completely 
change the design of my CipherList class, which could have repercussions throughout the program. I felt that it was not
necessary for the current version of Encoder, but maybe down the road it would be better.

While some changes could be made, I feel that the program as it does not require much change, as I did work on making
this as good as I could. Other than the JList issue, I do not think I need to do any refactoring, since I did end up
following most if not all the things we learned in 210. Each class for the most part, does one thing, maybe adding 
better specifications, and having better names might help with understanding what is happening everywhere, but its not 
necessary, due to the way I solved the problems. I tried making it as simple as I could, so I myself wouldn't get 
confused anywhere in the program, and it does appear to be simple, yet effective.