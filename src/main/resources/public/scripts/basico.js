const tipoSimples = 0, tipoLista = 1;

function bodyOnLoad() {
	if (err != "~ERRO~")
		alert(err);
	document.querySelectorAll("form").forEach(
		f => {
			f.addEventListener("submit", (e) => {
				post_req(e.target.attributes["end"].value, e.target);
				e.preventDefault();
			})
		}
	);
	try {
		if (populate != "~FETCH~") {
			fetch(populate, { method: "POST", body: fbody })
				.then(resp => resp.json())
				.then(json => processar(json));
		}
	} catch { }
}

function post_req(end, form) {
	const fd = new FormData(form);
	let data = {};
	for (const [k, v] of fd)
		data[k] = v;
	fetch(end, { method: "POST", body: JSON.stringify(data) })
		.then(resp => resp.json())
		.then(json => processar(json));
}

function processar(r) {
	if (r.tipo == tipoSimples)
		alert(JSON.stringify(r.valor));
	if (r.tipo == tipoLista)
		listar(r.valor);
}

function listar(arr) {
	const l = document.querySelector("#lista");
	l.innerHTML = "";
	for (const e of arr) {
		const li = document.createElement("li");
		li.textContent = JSON.stringify(e);
		l.appendChild(li);
	}
}