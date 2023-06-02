(() => {
    function post_req(end, form) {
        const fd = new FormData(form);
        let data = {};
        for (const [k, v] of fd)
            data[k] = v;
        fetch(end, { method: "POST", body: JSON.stringify(data) })
            .then(resp => resp.json())
            .then(json => sessionStorage.setItem("jwt", json));
    }

    function main() {
        document.querySelector("#btnlogar").addEventListener("click", e => { post_req("/login", e.target.parentElement) });
    }

    function onready(callback) {
        if (document.readyState != "loading")
            callback();
        else
            document.addEventListener("DOMContentLoaded", callback);
    }

    onready(main);
})()