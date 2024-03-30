function carregar() {
    msg = window.document.getElementById('mensagem');
    img = window.document.getElementById('imagem');
    horario = window.document.getElementById('horario');
    conteudo = window.document.getElementById('conteudo');


    var data = new Date();
    var hora = data.getHours();
    var min = data.getMinutes();
    var sec = data.getSeconds();

    if (hora >= 0 && hora < 12) {
        // Bom dia
        msg.innerHTML = `Agora são ${formatTime(hora)}:${formatTime(min)}:${formatTime(sec)} da manhã`;
        img.src = './images/manha.png';
        horario.style.background = '#0a95dff0';
        conteudo.style.background = 'linear-gradient(to bottom, #0a95df, #19d4f4)';
    } else if (hora >= 12 && hora < 19) {
        // Boa tarde
        msg.innerHTML = `Agora são ${formatTime(hora)}:${formatTime(min)}:${formatTime(sec)} da tarde`;
        img.src = './images/tarde.png';
        horario.style.background = '#fd6e12f0';
        conteudo.style.background = 'linear-gradient(to bottom, #bf2c0f, #fd6e12)';
    } else {
        // Boa noite
        msg.innerHTML = `Agora são ${formatTime(hora)}:${formatTime(min)}:${formatTime(sec)} da noite`;
        img.src = './images/noite.png';
        horario.style.background = '#07273bf0';
        conteudo.style.background = 'linear-gradient(to bottom, #07273b, #083251)';
    }
}

function formatTime(time) {
    return time < 10 ? `0${time}` : time;
}

setInterval(carregar, 1000);