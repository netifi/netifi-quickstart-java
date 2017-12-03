# proteus-quickstart
This project provides a scaffolding for getting up and running quickly with [Netifi Proteus](http://www.netifi.com/proteus.html).

The project comes with a simple client and service. The client sends the word `World` to the service and the service responds with the message `Hello, World!`.

## Prerequisites
This quickstart requires a running Netifi Proteus Router cluster. 

You can start a single node cluster with Docker using the following command:

    $ docker run -p 8001:8001 -p 7001:7001 -e MEMBERS_ADDRESS=172.17.0.2:7001 -e CLUSTER_PUBLIC_ADDRESS=172.17.0.2:7001 -e CLUSTER_ADDRESS=172.17.0.2 -e ROUTER_ADDRESS=172.17.0.2 netifi/proteus

Note the ports that are currently being assigned. You may need to modify them on your machine. If you modify the ports you will need to change them in the [client](/client/src/main/java/io/netifi/proteus/quickstart/client/Main.java) and [service](/service/src/main/java/io/netifi/proteus/quickstart/service/Main.java).

## Running the Quickstart
1. Validate that the Proteus Router is running.

2. Start the service using the following Gradle command:

        $ ./gradlew :service:run
        
3. Start the client using the following Gradle command:

        $ ./gradlew :client:run
        
4. If the client successfully called the service and received a response you will see the following in the console:

        > Task :client:run
        SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
        SLF4J: Defaulting to no-operation (NOP) logger implementation
        SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
        Sending 'World' to HelloService...
        message: "Hello, World!"
        
        BUILD SUCCESSFUL in 5s
        8 actionable tasks: 2 executed, 6 up-to-date

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/proteus-quickstart/issues).

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
