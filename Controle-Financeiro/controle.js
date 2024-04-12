const form = document.getElementById("form");

const descInput = document.getElementById("descricao");
const valueInput = document.getElementById("montante");

const balancoH1 = document.getElementById("balanco");

const receitaP = document.getElementById("din-positivo");
const despesaP = document.getElementById("din-negativo");

const transacoesUL = document.getElementById("transacoes");

form.addEventListener("submit", event => {
    // Não submete o formulário - Retira funcionalidade padrão do elemento
    event.preventDefault();

    const descTransacao = descInput.value.trim();
    const valueTransacao = valueInput.value.trim();

    if (descTransacao == '') {
        alert("[ERRO] O campo 'Descrição' não pode ser vazio!")
        descInput.focus();
        return;
    }

    if (valueTransacao == '') {
        alert("[ERRO] O campo 'Valor' não pode ser vazio!")
        valueTransacao.focus();
        return;
    }

    const transacao = {
        id: parseInt(Math.random() * 100000),
        desc: descTransacao,
        valor: parseFloat(valueTransacao)
    };

    somaAoSaldo(transacao);
    somaReceitaDespesa(transacao);
    addTransacaoAoDOM(transacao);

    descInput.value = '';
    valueInput.value = '';
});

function somaAoSaldo(transacao) {
    let valorBalanco = balancoH1.innerHTML.trim();
    valorBalanco = valorBalanco.replace("R$", "");

    valorBalanco = parseFloat(valorBalanco);
    valorBalanco += transacao.valor;
    balancoH1.innerHTML = `R$${valorBalanco.toFixed(2)}`;
}

function somaReceitaDespesa(transacao) {

    const elemento = transacao.valor > 0 ? receitaP : despesaP;
    const substituir = transacao.valor > 0 ? "+ R$" : "- R$";
    let valor = elemento.innerHTML.replace(substituir, "");
    valor = parseFloat(valor);
    valor += Math.abs(transacao.valor);
    elemento.innerHTML = `${substituir}${valor.toFixed(2)}`;
}

function addTransacaoAoDOM(transacao) {

    const cssClass = transacao.valor > 0 ? 'positivo' : 'negativo';
    const currency = transacao.valor > 0 ? '+R$' : "-R$";
    const liElementStr = `${transacao.desc} <span>${currency} ${Math.abs(transacao.valor)}</span><button class="delete-btn">X</button>`;

    const liElement = document.createElement('li');
    liElement.innerHTML = liElementStr;
    liElement.classList.add(cssClass);
    transacoesUL.appendChild(liElement);
}
