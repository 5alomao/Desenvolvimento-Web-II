// GLOBAL ESCOPE
// const nome = 'Salomão'
// let idade = 20
// var filhos = 0
// function printData() {
//     console.log(`F Nome: ${nome}, Idade: ${idade}, Filhos: ${filhos}`)
//     for (let i = 0; i < 100; i++) {
//         idade = 21
//     }
// }

// printData()
// console.log(`Nome: ${nome}, Idade: ${idade}, Filhos: ${filhos}`)

// var nome
// function globalAuto() {
//     nome = 'Salomão'
//     // Atribuição SEM declaração --> Faltou declarar para limitar escopo
//     idade = 20
// }

// globalAuto()
// console.log(`Nome: ${global.nome}, Idade: ${idade}`)

function scopeTest() {
    const brand = "Volvo"
    let model = "CX40"
    year = "2024"
    console.log(`${brand} ${model} ${year}`)
    // HOISTING - Alça declaração pro inicio - VAR
    var year
}

scopeTest()
console.log(`${brand} ${model} ${year}`)
