function addEventAddProductToBtn(){
    let addToShoppingCartBtns = document.querySelectorAll('div[class="img item_add"]');
    for(let i=0;i<addToShoppingCartBtns.length;i++){

        addToShoppingCartBtns[i].onclick = function(){
            let bundleElement = document.querySelectorAll('div[class="col-md-3 item-grid simpleCart_shelfItem"]')[i];
            let productId = bundleElement.querySelector('div[class="women-top"] h6 a').href.split('/')[4];
            if(!shoppingCart.isExist({ productId : productId })){
                let fullPathImg = bundleElement.querySelector('div[class="pro-img"] img').src;
                let arrPath = fullPathImg.split('localhost')[1].split('/');
                shoppingCart.insert({
                    brand : bundleElement.querySelector('div[class="women-top"] span').innerText,
                    name : bundleElement.querySelector('div[class="women-top"] h6 a').innerText,
                    price : bundleElement.querySelector('em[class="item_price"]').innerText,
                    productId : productId,
                    quantity: "1",
                    avatar : arrPath.slice(1,arrPath.length).join('/')
                });
            }
            else {
                let sl = shoppingCart.where({productId: productId}).quantity;
                sl = Number(sl)+1;
                shoppingCart.update({productId : productId},{quantity: sl.toString()});
            }
        }
    }
}
addEventAddProductToBtn();