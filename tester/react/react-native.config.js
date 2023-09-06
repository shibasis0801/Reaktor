const ios = require('@react-native-community/cli-platform-ios');
const android = require('@react-native-community/cli-platform-android');

// https://github.com/react-native-community/cli/blob/main/docs/platforms.md
module.exports = {
    project: {
        android: {
            sourceDir: "../android",
        },
        ios: {
            sourceDir: "../ios",
        }
    }
};