const chave_transacoes_ls = "transacoes";
const chave_id_ls = "id";

const form = document.getElementById("form");
const btnRemover = document.getElementsByClassName("delete-btn");

const descInput = document.getElementById("descricao");
const valueInput = document.getElementById("montante");
const sinalInput = document.getElementsByName("radSinal");

const titleSaldo = document.getElementById("item-title-saldo");

const receitaP = document.getElementById("din-positivo");
const despesaP = document.getElementById("din-negativo");

const transacoesUL = document.getElementById("transacoes");

let transacoesSalvas = null;
let idSalvos = null;

if (localStorage.getItem(chave_id_ls) != "") {
  try {
    idSalvos = JSON.parse(localStorage.getItem(chave_id_ls));
  } catch (err) {
    console.log("Erro:", err);
  }
}

if (localStorage.getItem(chave_transacoes_ls) != "") {
  try {
    transacoesSalvas = JSON.parse(localStorage.getItem(chave_transacoes_ls));
  } catch (err) {
    console.log("Erro:", err);
  }
}

if (idSalvos === null) {
  idSalvos = [];
}

if (transacoesSalvas === null) {
  transacoesSalvas = [];
}

form.addEventListener("submit", (event) => {
  // Não submete o formulário - Retira funcionalidade padrão do elemento
  event.preventDefault();

  const descTransacao = descInput.value.trim();
  let valueTransacao = valueInput.value.trim();

  if (descTransacao == "") {
    alert("[ERRO] O campo 'Descrição' não pode ser vazio!");
    descInput.focus();
    return;
  }

  if (valueTransacao == "") {
    alert("[ERRO] O campo 'Valor' não pode ser vazio!");
    valueTransacao.focus();
    return;
  }

  valueTransacao = Math.abs(valueTransacao);

  if (sinalInput[0].checked) {
    valueTransacao *= 1;
  } else if (sinalInput[1].checked) {
    valueTransacao *= -1;
  } else {
    alert("[ERRO] O campo de 'Entrada' deve ser escolhido!");
    return;
  }
  let transacaoID = idSalvos.length;
  const transacao = {
    id: transacaoID,
    desc: descTransacao,
    valor: parseFloat(valueTransacao),
  };

  somaAoSaldo(transacao);
  somaReceitaDespesa(transacao);
  addTransacaoAoDOM(transacao);
  // Adicionando ao vetor de transações
  idSalvos.push(transacao.id);
  localStorage.setItem(chave_id_ls, JSON.stringify(idSalvos));
  console.log(idSalvos.length);
  transacoesSalvas.push(transacao);
  localStorage.setItem(chave_transacoes_ls, JSON.stringify(transacoesSalvas));
});

function setTitleColor(valorBalanco) {
  if (parseFloat(valorBalanco) < 0) {
    titleSaldo.style.color = "red";
  } else if (parseFloat(valorBalanco) > 0) {
    titleSaldo.style.color = "#2ecc71";
  } else {
    titleSaldo.style.color = "#e7e7e7";
  }
}

function setTitleText(valorBalanco) {
  if (parseFloat(valorBalanco) < 0) {
    titleSaldo.innerHTML = `R$${valorBalanco.toFixed(2)}`;
  } else if (parseFloat(valorBalanco) > 0) {
    titleSaldo.innerHTML = `R$${valorBalanco.toFixed(2)}`;
  } else {
    titleSaldo.innerHTML = `R$0.00`;
  }
}

function somaAoSaldo(transacao) {
  let valorBalanco = titleSaldo.innerHTML.trim();
  valorBalanco = valorBalanco.replace("R$", "");
  valorBalanco = parseFloat(valorBalanco);
  valorBalanco += transacao.valor;
  setTitleColor(valorBalanco);
  setTitleText(valorBalanco);
}

function subtraiAoSaldo(transacao) {
  let valorBalanco = titleSaldo.innerHTML.trim();
  valorBalanco = valorBalanco.replace("R$", "");
  valorBalanco = parseFloat(valorBalanco);
  valorBalanco -= transacao.valor;
  setTitleColor(valorBalanco);
  setTitleText(valorBalanco);
}

function somaReceitaDespesa(transacao) {
  const elemento = transacao.valor > 0 ? receitaP : despesaP;
  const substituir = transacao.valor > 0 ? "+ R$" : "- R$";
  let valor = elemento.innerHTML.replace(substituir, "");
  valor = parseFloat(valor);
  valor += Math.abs(transacao.valor);
  elemento.innerHTML = `${substituir}${valor.toFixed(2)}`;
}

function subtraiReceitaDespesa(transacao) {
  const elemento = transacao.valor > 0 ? receitaP : despesaP;
  const substituir = transacao.valor > 0 ? "+ R$" : "- R$";
  let valor = elemento.innerHTML.replace(substituir, "");
  valor = parseFloat(valor);
  valor -= Math.abs(transacao.valor);
  elemento.innerHTML = `${substituir}${valor.toFixed(2)}`;
}

function addTransacaoAoDOM(transacao) {
  const cssClass = transacao.valor > 0 ? "positivo" : "negativo";
  const currency = transacao.valor > 0 ? "+R$" : "-R$";
  const liElementStr = `${transacao.id}:${
    transacao.desc
  } <span>${currency} ${Math.abs(
    transacao.valor
  )}</span><button class="delete-btn" onclick="deletaTransacao('${
    transacao.id
  }')">X</button>`;
  const liElement = document.createElement("li");
  liElement.setAttribute("data-id", transacao.id);
  liElement.innerHTML = liElementStr;
  liElement.classList.add(cssClass);
  transacoesUL.appendChild(liElement);
  console.log("Elemento adicionado na UL");
  console.log(transacoesSalvas);
  descInput.value = "";
  valueInput.value = "";
  for (radioIndex in sinalInput) {
    sinalInput[radioIndex].checked = false;
  }
}

function deletaTransacao(id) {
  const transacaoIndex = transacoesSalvas.findIndex(
    (transacao) => transacao.id == id
  );

  if (transacaoIndex === -1) {
    console.log("Transação não encontrada");
    return;
  }

  const liToRemove = document.querySelector(`li[data-id="${id}"]`);
  if (liToRemove) {
    liToRemove.remove();
    subtraiAoSaldo(transacoesSalvas[transacaoIndex]);
    subtraiReceitaDespesa(transacoesSalvas[transacaoIndex]);
    console.log("Elemento <li> removido do DOM");
    transacoesSalvas.splice(transacaoIndex, 1);
    localStorage.setItem(chave_transacoes_ls, JSON.stringify(transacoesSalvas));
    console.log("Elemento removido do LocalStorage");
  } else {
    console.log("Elemento <li> não encontrado no DOM");
  }
}

function carregarDados() {
  // transacoesUL.innerHTML = "";
  // titleSaldo.innerHTML = "R$0.00";
  // receitaP.innerHTML = "+ R$0.00";
  // despesaP.innerHTML = "- R$0.00";

  for (transacao of transacoesSalvas) {
    somaAoSaldo(transacao);
    somaReceitaDespesa(transacao);
    addTransacaoAoDOM(transacao);
  }
}

carregarDados();
console.log(transacoesSalvas);
console.log(idSalvos);
localStorage.clear();
