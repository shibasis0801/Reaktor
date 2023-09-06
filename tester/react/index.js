import React from "react";
import {
    requireNativeComponent,
    StyleSheet,
    AppRegistry,
    View,
    Text,
    Dimensions,
    NativeModules,
    SafeAreaView
} from 'react-native';
import {FeedScreen} from "@mehmaan/feed";
import {MD3DarkTheme, MD3LightTheme, PaperProvider} from "react-native-paper";

global.Flow = class Flow {
    observer = null
    emit = (value)=> {
        if (this.observer != null)
            this.observer(value)
    }
    collect = (observer) => {
        this.observer = observer
    }
}

// const JSIManager = NativeModules.JSIManager;
// JSIManager.install()
//
// console.log("Shibasis", global.NetworkModule, global.MehmaanConfig, global.FeedViewModel)
// global.FeedViewModel.getFeed().collect(data => console.log("FeedViewModel: ", data))

// console.log("SHIBASIS", NativeModules.CalendarManager.getConstants(), NativeModules.CalendarManager.getData("", "", 1))


// console.log(global.NetworkModule)
// global.NetworkModule.get().collect(data => console.log("Flow: ", data))

AppRegistry.registerComponent("Mehmaan", () => () => {
    // use lib-navigation instead of writing here, only the theme should be supplied from here
    // Theme should be same for compose and react
    return (
        <SafeAreaView>
            <PaperProvider theme={MD3LightTheme}>
                <FeedScreen />
            </PaperProvider>
        </SafeAreaView>
    )
})
