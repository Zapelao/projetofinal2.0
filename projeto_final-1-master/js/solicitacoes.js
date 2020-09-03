function gravar(){

    var data = document.getElementById("txtdata").value;
    var ano = data.substring(0,4);
    var mes = data.substring(5,7);
    var dia = data.substring(8);
    var nossadata = dia + "/" + mes + "/" + ano;
     
    var mensagem = {
        nomeTec : document.getElementById("txtnome").value ,
        operadora : document.getElementById("txtemail").value ,
        telefone : document.getElementById("txtcelular").value,
        doc : document.getElementById("txtdocumento").value,
        data : nossadata,
        hora : document.getElementById("txthora").value,
        status: "1" ,
        pdvId : {
          id : document.getElementById("cmbpdv").value
        }
    }

    var cabecalho = {
        method:"POST",
        body: JSON.stringify(mensagem),
        headers:{
            "Content-Type": "application/json"
        }
    }

    fetch("http://localhost:8080/solicitacao/novasolicitacao", cabecalho)
        .then(res => res.json())
        .then(res => {
            window.alert("Gravado com sucesso");
            window.location="solicitacoes.html";
        })
        .catch(err => {
            window.alert("Erro");
        });
}

function preencherCombo(lista){
  var dados = "";
  for(cont=0;cont<lista.length;cont++){
      dados += "<option value='"+lista[cont].id+"'>" + lista[cont].numPonto +" - " + lista[cont].nome +"</option>";
  }
  document.getElementById("cmbpdv").innerHTML=dados;
}
function carregarPdv(){
  fetch("http://localhost:8080/pdv/consulta/pdvs").then(res=>res.json()).then(res=>preencherCombo(res))
}

function mask(o, f) {
    setTimeout(function() {
      var v = mphone(o.value);
      if (v != o.value) {
        o.value = v;
      }
    }, 1);
  }
  
  function mphone(v) {
    var r = v.replace(/\D/g, "");
    r = r.replace(/^0/, "");
    if (r.length > 10) {
      r = r.replace(/^(\d\d)(\d{5})(\d{4}).*/, "($1) $2-$3");
    } else if (r.length > 5) {
      r = r.replace(/^(\d\d)(\d{4})(\d{0,4}).*/, "($1) $2-$3");
    } else if (r.length > 2) {
      r = r.replace(/^(\d\d)(\d{0,5})/, "($1) $2");
    } else {
      r = r.replace(/^(\d*)/, "($1");
    }
    return r;
  }