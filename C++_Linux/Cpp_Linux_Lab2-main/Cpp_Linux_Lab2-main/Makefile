bin=cipher
lib=libcipher.so

# Set the following to '0' to disable log messages:
LOGGER ?= 1

# Compiler/linker flags
CFLAGS += -g -Wall -fPIC -DLOGGER=$(LOGGER)
LDLIBS += 
LDFLAGS +=

src=cipher.c
obj=$(src:.c=.o)

all: $(bin) $(lib)

$(bin): $(obj)
	$(CC) $(CFLAGS) $(LDLIBS) $(LDFLAGS) $(obj) -o $@

$(lib): $(obj)
	$(CC) $(CFLAGS) $(LDLIBS) $(LDFLAGS) $(obj) -shared -o $@

cipher.o: cipher.c logger.h

clean:
	rm -f $(bin) $(lib) $(obj) libshell.so vgcore.*


# Tests --

test: $(bin) $(lib) ./tests/run_tests
	@DEBUG="$(debug)" ./tests/run_tests $(run)

testupdate: testclean test

./tests/run_tests:
	rm -rf tests
	git clone https://github.com/usf-cs521-sp21/L2-Tests.git tests

testclean:
	rm -rf tests
