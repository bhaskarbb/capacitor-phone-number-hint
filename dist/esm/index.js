import { registerPlugin } from '@capacitor/core';
const PhoneNumberHint = registerPlugin('PhoneNumberHint', {
    web: () => import('./web').then(m => new m.PhoneNumberHintWeb()),
});
export * from './definitions';
export { PhoneNumberHint };
//# sourceMappingURL=index.js.map