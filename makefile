# Project: cvm
# Makefile created by Dev-C++ 5.11

CPP      = g++
OBJ      = main.o cvm-stack.o cvm-vm.o cvm-handler.o cvm-module.o twofish.o
LINKOBJ  = main.o cvm-stack.o cvm-vm.o cvm-handler.o cvm-module.o twofish.o
LIBS     = -static-libstdc++ -s
BIN      = cvm
CXXFLAGS = $(CXXINCS) -std=c++11
CFLAGS   = $(INCS) -std=c++11
RM       = rm -f

.PHONY: all all-before all-after clean clean-custom

all: all-before $(BIN) all-after

clean: clean-custom
	${RM} $(OBJ) $(BIN)

$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o $(BIN) $(LIBS)

main.o: main.cpp
	$(CPP) -c main.cpp -o main.o $(CXXFLAGS)

cvm-stack.o: cvm-stack.cpp
	$(CPP) -c cvm-stack.cpp -o cvm-stack.o $(CXXFLAGS)

cvm-vm.o: cvm-vm.cpp
	$(CPP) -c cvm-vm.cpp -o cvm-vm.o $(CXXFLAGS)

cvm-handler.o: cvm-handler.cpp
	$(CPP) -c cvm-handler.cpp -o cvm-handler.o $(CXXFLAGS)

cvm-module.o: cvm-module.cpp
	$(CPP) -c cvm-module.cpp -o cvm-module.o $(CXXFLAGS)

twofish.o: twofish.cpp
	$(CPP) -c twofish.cpp -o twofish.o $(CXXFLAGS)