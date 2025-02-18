var capacitorPhoneNumberHint = (function (exports, core) {
    'use strict';

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

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
