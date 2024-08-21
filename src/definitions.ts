export interface PhoneNumberHintPlugin {
  getPhoneNumber(): Promise<{ phoneNumber: string }>;
}
