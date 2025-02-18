import { WebPlugin } from '@capacitor/core';
import type { PhoneNumberHintPlugin } from './definitions';
export declare class PhoneNumberHintWeb extends WebPlugin implements PhoneNumberHintPlugin {
    getPhoneNumber(): Promise<{
        phoneNumber: string;
    }>;
}
