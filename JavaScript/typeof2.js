let nula = null;
let indefinida = undefined;

console.log(nula == indefinida) // true
console.log(nula === indefinida) // false
console.log(10 == '10') // true
console.log(10 === '10') // false

console.log(nula ? 'verdadeira' : 'falsa')
console.log(indefinida ? 'verdadeira' : 'falsa')

console.log(nula == false) // false
console.log(true && nula) // false

console.log(2 + indefinida) //NaN
console.log(2 + nula) // 2