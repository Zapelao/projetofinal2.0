function exibirUsuario(){
    var strusuario = localStorage.getItem("user");
    if (strusuario){
        var usuario = JSON.parse(localStorage.getItem("user"));
        document.getElementById("foto").innerHTML = "<img alt='Sem foto' width='50%' src=../img/" + usuario.foto + ">";
        document.getElementById("dados").innerHTML = "<h3>" + usuario.nome + " (" + usuario.racf + ")" + "</h3>";
        this.carregarSolicitacoes();
    } else{
        window.location="../html/login.html";
    }
}

function deslogar(){
    localStorage.removeItem("user");
    window.location="../html/login.html"
}

function preencherTabela(lista){

    var tabela=
    "<div class='row'>" +
    "<div class='col-md-12 col-sm-12 col-xs-12'>" +
    "<table border='2' align='center' width='100%' cellspacing='2' style='font-size: 70%'>" + 
    "<tr width='100%'>" + 
    "<th>Tecnico</th>" +
    "<th>Operadora</th>" + 
    "<th>Pdv</th>" +
    "<th>Data / Hora</th>" +
    "<th>Status</th>" +
    "<th>Ações</th>" +
    "</tr>";
    
    for(cont=0;cont<lista.length;cont++){
        var status = "";
        if (lista[cont].status == 1){
            status = "INICIAL";
        }else if (lista[cont].status == 2){
            status = "APROVADA";
        }else if (lista[cont].status == 3){
            status = "NEGADA";
        }else{
            status = "CANCELADA";
        }
        tabela+=
            "<tr width='100%'>" +
            "<td style='font-size: 100%'>" + lista[cont].nomeTec + "</td>" + 
            "<td style='font-size: 100%'>" + lista[cont].operadora + "</td>" + 
            "<td style='font-size: 100%'>" + lista[cont].pdvId.numPonto + " - " + lista[cont].pdvId.nome + "</td>" +  
            "<td style='font-size: 100%'>" + lista[cont].data+ " / " + lista[cont].hora + "</td>" +
            "<td style='font-size: 100%'>" + status + "</td>" + 
            "<td>" +    "<button type='button' width='25%' class='btn btn-success' style='font-size: 100%' onclick='autorizar("+lista[cont].numSeq+")'>Autorizar</button>" +
                        "<button type='button' width='25%' class='btn btn-warning' style='font-size: 100%' onclick='negar("+lista[cont].numSeq+")'>Negar</button>" +
                        "<button type='button' width='25%' class='btn btn-danger'  style='font-size: 100%' onclick='cancelar("+lista[cont].numSeq+")'>Cancelar</button>" +
            "</td>"+
            "</tr>";
    }

    tabela+="</table></div></div>";

    document.getElementById("solicitacoes").innerHTML=tabela;
}
function carregarSolicitacoes(){
    fetch("http://localhost:8080/solicitacao/consulta").then(res=>res.json()).then(res=>preencherTabela(res))
}
function carregarSolicitacoesStatus(){
    var combo = document.getElementById("cbostatus");
	var status = combo.options[combo.selectedIndex].value;
    if (status == 0){
        carregarSolicitacoes();
    }else{
        fetch("http://localhost:8080/solicitacao/consulta/status/"+status).then(res=>res.json()).then(res=>preencherTabela(res))
        .catch(err => {
            window.alert("Não há solicitacoes com este status");
        });
    }
}

function autorizar(solicitacao){

    var mensagem = {
        numSeq : solicitacao ,
        status: "2"
    }

    var cabecalho = {
        method:"POST",
        body: JSON.stringify(mensagem),
        headers:{
            "Content-Type": "application/json"
        }
    }

    fetch("http://localhost:8080/solicitacao/atualizarstatus", cabecalho)
        .then(res => res.json())
        .then(res => {
            window.alert("SMS enviado - Prosseguir");
            carregarSolicitacoes();
        })
        .catch(err => {
            window.alert("Erro");
        });

}

function negar(solicitacao){

    var mensagem = {
        numSeq : solicitacao ,
        status: "3"
    }

    var cabecalho = {
        method:"POST",
        body: JSON.stringify(mensagem),
        headers:{
            "Content-Type": "application/json"
        }
    }

    fetch("http://localhost:8080/solicitacao/atualizarstatus", cabecalho)
        .then(res => res.json())
        .then(res => {
            window.alert("SMS enviado - Solicitação negada");
            carregarSolicitacoes();
        })
        .catch(err => {
            window.alert("Erro");
        });

}

function cancelar(solicitacao){

    var mensagem = {
        numSeq : solicitacao ,
        status: "4"
    }

    var cabecalho = {
        method:"POST",
        body: JSON.stringify(mensagem),
        headers:{
            "Content-Type": "application/json"
        }
    }

    fetch("http://localhost:8080/solicitacao/atualizarstatus", cabecalho)
        .then(res => res.json())
        .then(res => {
            window.alert("SMS enviado - Solicitação cancelada");
            carregarSolicitacoes();
        })
        .catch(err => {
            window.alert("Erro");
        });

}