function validar() {
	let nome = frmContato.nome.value;
	let fone = frmContato.fone.value;
	
	if(nome === '') {
		alert('Digite o campo "Nome"');
		frmContato.nome.setFocus();
		return false;
	}else if(fone === '') {
		alert('Digite o campo "Fone"');
		frmContato.fone.setFocus();
		return false;
	}else {
		document.forms["frmContato"].submit();
	}
	
}