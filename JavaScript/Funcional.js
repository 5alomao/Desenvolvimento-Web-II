// Vetor
const numeros = [1, 2, 3, 4, 5];

// Procedural
const quadrados = [];

for (let i of numeros) {
    let quadradoNum = i * i;
    quadrados.push(quadradoNum);
}
console.log(numeros);
console.log(quadrados);


// Funcional
// Função lambda - sem nome - anonimas - sem chamadas
const quadrados2 = numeros.map((n) => n * n);
console.log(quadrados2)