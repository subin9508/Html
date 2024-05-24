/**
 * while.html에 포함
 */

 const list = document.querySelector('#list');
 // document.getElementById('list');
 const tableBody = document.querySelector('#tableBody');
 
 let html = ''; // <ul></ul> 태그의 컨텐츠로 작성할 HTML 코드.
 
let n = 1;
while (n <= 5) {
    html += `<li> 아이템 ${n} </li>`;
    n++;
}
list.innerHTML = html;


html = '';
let x = 1;
while (x <= 5) {
    html += `
     <tr>
        <td> ${x} </td> 
        <td> 아이템 </td>
     </tr>`;
    x++;
}
tableBody.innerHTML += html;