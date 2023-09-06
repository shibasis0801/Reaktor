/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */
const blockList = require('metro-config/src/defaults/exclusionList');
const path = require('path')

console.log(__dirname)
const watchFolders = [
  path.resolve(path.join(__dirname, '../../modules')),
  path.resolve(path.join(__dirname, '../../node_modules'))
];

// const { $ } = require("zx")

// const folders = await $`ls`
// console.log("SHIBASIS SHIBASIS")
// console.log(folders)
const allowList = [
  'platforms',
  'modules',
  'index.ts',
  'metro.config.js',
  'babel.config.js',
  'package.json',
  'node_modules'
];

const escapedFolders = allowList.map(folder => {
  const folderPath = path.join(__dirname, folder);
  // Escape slashes for use in a regular expression
  return folderPath.replace(/[\\\/]/g, '[\\\\\\/]');
});

const blockListRe = new RegExp(
    `^((?!${escapedFolders.join('|')}).)*$`,
);

// This regex blocklist is very dangerous, right now it is working
// But we need a solid solution for allowlist
// If you get an issue pretty-format

module.exports = {
  resolver: {
    sourceExts: ['jsx', 'js', 'ts', 'tsx', 'cjs', 'json'],
    blockList: blockList([
      /build\/js\/.*/,
      /build\/productionLibrary.*/,
      /build\/tmp\/.*/,
    ])
  },
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true,
      },
    }),
  },
  watchFolders
};
