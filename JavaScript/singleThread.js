function f6() {
    let value = 11;
    while (value > 1) {
        value = Math.random() * 100000
        console.log(value)
    }
    console.log('f6() rodou')
}

function f5() {
    console.log('f5() rodou')
    f6()
}
// Colocar a função para executar em call back, não entra na pilha de execução diretamente
// Chamada assíncrona
// setTimeout(() => console.log('f5 rodou'), 5000)
function f4() {
    console.log('f4() rodou')
    setTimeout(f5, 1500);
}

function f3() {
    console.log('f3() rodou')
    setTimeout(f4, 1500)
}

function f2() {
    console.log('f2() rodou')
    f3()
}

function f1() {
    console.log('f1() rodou')
    f2();
}

f1();

console.log("Término")

// 3 tipos de criação de strings normais
let name = 'Salomão'
let midle = String("20 anos")
let family = new String("Ferreira")

console.log(`${name} ${family}: ${midle}`)
console.log(typeof(name)) //string
console.log(typeof(midle)) // string
console.log(typeof(family)) // object