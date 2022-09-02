# Reciprocal Cipher

After selling a large amount of `$GME` and fleeing the country to a remote island, you decide your next best course of action is to convert all of your cash to bitcoin. The only downside is your vast wealth will now be protected by a single password to your bitcoin wallet.

Knowing that you tend to be forgetful, but not wanting to simply write the password down on a scrap of paper (local turtles or seagulls on the island may see it), you decide to devise an encryption scheme.

Standard advice in the crypto community is that you should **always** write your own encryption libraries, never using off-the-shelf solutions vetted by experts. What do they know anyway?! You find an article on [ROT-13](https://en.wikipedia.org/wiki/ROT13) and inspiration takes hold: you'll use a reciprocal letter substitution cipher to protect your password!

## Getting Started

`cipher.c` provides more detail on your encryption scheme.

You can use `make` to compile the program. Here are a few examples on how to run it:

```
# Encrypts 'file.txt':
./cipher < file.txt

# Encrypts the string 'hello world':
echo "hello world" | ./cipher

# Encrypts and saves the output to a file:
./cipher < file.txt > encrypted.txt

# Decrypts a file (same as encrypting):
./cipher < encrypted.txt
```


## Testing Your Code

You can run `make test` to run all the test cases, or `make test run=X` to run a specific test case, where `X` is the test case number.

If the professor does a bad job writing the test cases and updates them, you can pull in the new test cases with `make testupdate`.

