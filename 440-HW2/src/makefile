JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
        clients/ContainerClient.java \
        communication/RMI440.java \
        communication/RMIMessage.java \
        examples/Container_Stub.java \
        examples/Container.java \
        examples/Element.java \
        examples/ElementImpl.java \
        examples/RemoteContainerImpl.java \
        examples/RemoteElement_Stub.java \
        examples/RemoteElement.java \
        examples/RemoteElementImpl.java \
        references/RemoteObjectReference.java \
        references/RORTable440.java \
        registry/LocateRegistry.java \
        registry/Registry440.java \
        registry/RegistryCom440.java \
        registry/RegistryImpl.java \
        stub/Remote440.java \
        stub/RemoteException440.java \
        stub/RemoteStub440.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class