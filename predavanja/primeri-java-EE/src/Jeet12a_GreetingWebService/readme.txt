To generate Web Services classes, open your command line, 
and type :

cd %project_home%
wsgen -s src -d build/classes -cp build/classes juma.mohammad.GreetingImpl

Now you have two classes (SayHello.java, SayHelloResponse.java) 
generated under /greetingWS/src/juma/mohammad/jaxws  