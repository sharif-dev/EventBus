# EventBus Method

One of the most important part of developing an app is the connection between different parts. Communication between different fragments on android should not be done directly. There are several ways to generate this connection, and the EventBus method is one of them. We will further explore this method.

## EventBus Overview

We know that in android, fragments represent UI behavior, and each fragment is associated with several activities. Thus, if an activity disappears, all fragments associated with that activity will be lost. This method makes communication between components easier, simplifies your code, make it faster, and eliminates error-prone complexities and dependencies.
This method is a messaging system between the subscriber and the publisher. The subscriber is waiting for an event to be sent by the publisher. The picture below illustrates this.


<p align="center">
<img src="https://raw.githubusercontent.com/greenrobot/EventBus/master/EventBus-Publish-Subscribe.png" width="500" height="200">
</p>


When the registry goes up (with onCreate), one of the things it does is to set up EventBus between the controller and the activity to get ready for the events to be exchanged. 

When the project is built and run, the registry creates the controllers, and then set up EventBus between the controller and the activities to start sending and receiveing events. 
The controller is the only component that comminucates with the activities in the app layer with EventBus. Therefore, each component of the app layer can interact with the controller and one of the other components of the app layer indirectly through EventBus.
<p align="center">
<img src="https://miro.medium.com/max/694/1*e8yug1SyMSMz_-V5az0YsQ.png" width="500" height="500">
</p>

## implementing Eventbus
1. Add EventBus dependency in app/build.gradle file


``` javascript
dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
    // Event Bus Library
    implementation 'org.greenrobot:eventbus:3.2.0'
}
```
2. Define events:
```javascript
public static class MessageEvent { 
  public final String message;
 
    public MessageEvent(String message) {
        this.message = message;
    }
}
```

3. Create subscribers: (optionally specify a thread mode)
```javascript
@Subscribe(threadMode = ThreadMode.MAIN)  
public void onMessageEvent(MessageEvent event) {/* Do something */};
```
Create the start and stop function of the subscriber
```javascript
@Override
public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
}
 
@Override
public void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
}
 ```
 
 4. Post events:
```javascript
EventBus.getDefault().post(new MessageEvent("Hello World!"));
```
