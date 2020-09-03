function logar(){
    var mensagem = {
        email:document.getElementById("txtusuario").value, 
        senha:document.getElementById("txtsenha").value
    };

    var cabecalho = {
        method:"POST",
        body:JSON.stringify(mensagem),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/usuario/login",cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("user",JSON.stringify(res));
            window.location="../html/supervisor.html";
        })
        .catch(err=>{
            window.alert("Digite usuario corretamente");
        });

}



