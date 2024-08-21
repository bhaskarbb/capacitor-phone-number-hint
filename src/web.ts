import { WebPlugin } from '@capacitor/core';

import type { PhoneNumberHintPlugin } from './definitions';

export class PhoneNumberHintWeb
  extends WebPlugin
  implements PhoneNumberHintPlugin
{
  async getPhoneNumber(): Promise<{ phoneNumber: string }> {
    throw new Error('Method not implmented');
  }
}
