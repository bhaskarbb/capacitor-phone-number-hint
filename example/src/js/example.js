import { PhoneNumberHint } from 'phone-number-hint';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    PhoneNumberHint.echo({ value: inputValue })
}
