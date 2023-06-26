let captcha;
let alphabets = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
console.log(alphabets.length);
let status = document.getElementById('status');
status.innerText = "Captcha Generator";

generateCaptcha = () => {
	// console.log(status)
	let first = alphabets[Math.floor(Math.random() * alphabets.length)];
	let second = Math.floor(Math.random() * 10);
	let third = Math.floor(Math.random() * 10);
	let fourth = alphabets[Math.floor(Math.random() * alphabets.length)];
	let fifth = alphabets[Math.floor(Math.random() * alphabets.length)];
	let sixth = Math.floor(Math.random() * 10);
	captcha = first.toString() + second.toString() + third.toString() + fourth.toString() + fifth.toString() + sixth.toString();
	console.log(captcha);
	document.getElementById('generated-captcha').value = captcha;
	document.getElementById("entered-captcha").value = '';
	status.innerText = "Ingrese el código"
}

checkCaptcha = () => {
	// console.log(status)
	let userValue = document.getElementById("entered-captcha").value;
	console.log(captcha);
	console.log(userValue);
	if (userValue == captcha) {
		status.innerText = "Correcto!!"
		return true;
	} 
	else {
		status.innerText = "Incorrecto!!"
		document.getElementById("entered-captcha").value = '';
		return false;
	}
}