# proteus-quickstart
This project provides a scaffolding for getting up and running quickly with [Netifi Proteus](http://www.netifi.com/proteus.html).

The project comes with a simple client and service. The client sends the word `World` to the service and the service responds with the message `Hello, World! from {service name}`.

## Prerequisites
This quickstart requires the following:

1. Java Development Kit 8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2. Protocol Buffers Compiler

    You can download the compiler from here: https://github.com/google/protobuf/releases
    
    OR
    
    If you are on a mac using homebrew you can install it with the following command:
    
        $ brew install protobuf

3. A running instance of the Netifi Proteus Router

    You can start a single node cluster with Docker using the following command:
    
        $ docker run -p 8001:8001 -p 7001:7001 -e MEMBERS_ADDRESS=172.17.0.2:7001 -e CLUSTER_PUBLIC_ADDRESS=172.17.0.2:7001 -e CLUSTER_ADDRESS=172.17.0.2 -e ROUTER_ADDRESS=172.17.0.2 netifi/proteus
    
    Note the ports that are currently being assigned. You may need to modify them on your machine. If you modify the ports you will need to change them in the [client](/client/src/main/java/io/netifi/proteus/quickstart/client/Main.java) and [service](/service/src/main/java/io/netifi/proteus/quickstart/service/Main.java).

## Running the Quickstart
The instructions below start two instances of the example service and requests from the client are automatically load balanced between the service instances.

1. Validate that the Proteus Router is running.

    Check the console for a successful start message:
    
        2017-12-03 21:36:04,067 INFO i.n.r.Bootstrap [main] Router started in 2266.12 milliseconds

2. Start the first instance of the service using the following Gradle command:

        $ ./gradlew :service:run
        
3. In a new terminal, start the second instance of the service using the same Gradle command.

        $ ./gradlew :service:run
        
4. Start the client using the following Gradle command:

        $ ./gradlew :client:run
        
5. If the client successfully called the service and received a response you will see the following in the console:

        > Task :client:run
        SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
        SLF4J: Defaulting to no-operation (NOP) logger implementation
        SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
        Sending 'World' to HelloService...
        message: "Hello, World! from helloservice-a90a4fc7-74a3-4019-9263-3116332c5aed"
        
        
        
        BUILD SUCCESSFUL in 1s
        8 actionable tasks: 1 executed, 7 up-to-date

6. Run the client multiple times to see the requests load balanced between the service instances.

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/netifi/proteus-quickstart/issues).

## License
Copyright 2017 Netifi Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
