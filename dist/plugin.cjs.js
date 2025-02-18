'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

const PhoneNumberHint = core.registerPlugin('PhoneNumberHint', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.PhoneNumberHintWeb()),
});

class PhoneNumberHintWeb extends core.WebPlugin {
    async getPhoneNumber() {
        throw new Error('Method not implmented');
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    PhoneNumberHintWeb: PhoneNumberHintWeb
});

exports.PhoneNumberHint = PhoneNumberHint;
//# sourceMappingURL=plugin.cjs.js.map
