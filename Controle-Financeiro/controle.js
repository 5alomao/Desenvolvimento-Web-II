const form = document.getElementById("form");

const descInput = document.getElementById("descricao");
const valueInput = document.getElementById("montante");

const balancoH1 = document.getElementById("balanco");


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
        value: valueTransacao
    };

    somaAoSaldo(transacao);

    descInput.value = '';
    valueInput.value = '';
});

function somaAoSaldo(transacao) {
    // Recuperar Saldo
    // Pegar Saldo, Remover o R$
    let valorBalanco = balancoH1.innerHTML.trim();
    valorBalanco = valorBalanco.replace("R$", "");
    valorBalanco = parseFloat(valorBalanco);
    // TO DO ---
}
