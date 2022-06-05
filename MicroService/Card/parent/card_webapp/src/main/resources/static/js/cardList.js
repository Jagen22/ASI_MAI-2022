// Usefull functions :

// GetAllCards, GetUserByID

if (!localStorage.hasOwnProperty('myUser')){
    location.assign("login.html");
}

var myUser = localStorage.getItem("myUser");
myUser = JSON.parse(myUser);
var userID = myUser.userID;

console.log("myUser :", myUser);
console.log("userID :", userID);

async function GetAllCards(){
    return fetch("/card")
    .then(response => response.json())
    .then(allCards => {
        console.log(allCards);
        return allCards;
    })
    .catch(error => console.error(error));
}

async function GetUserByID(userID){
    return fetch("/user/"+userID)
    .then(response => response.json())
    .then(user => {
        console.log("Success getser :", user);
        return user;
    })
    .catch(error => console.error(error));
}

// if buy : GetCardsToBuy, BuyCard,

async function GetCardsToBuy(userID){
    var allCards = await GetAllCards();
    var cardsToBuy = [];
    allCards.array.forEach(card => {
        if (card.cardID.owner.id != userID){
            cardsToBuy.push(card);
        }
    });
    return cardsToBuy;
}

async function BuyCard(cardID, userID){
    fetch("/market/buy/"+cardID+'/'+userID, {
                                                method:'POST',
                                                headers: new Headers(),
                                            }
    )
    .then(response => {
        console.log("Success BuyCard ");
        alert("Card bought succesfully");
        UpdateMoney(userID);
    })
    .catch(error => console.error(error));
}


// if sell : GetCardsToSell, SellCard

async function GetCardsToSell(userID){
    var allCards = await GetAllCards();
    var userCards = [];
    allCards.array.forEach(card => {
        if (card.cardID.owner.id != userID){
            userCards.push(card);
        }
    });
    return userCards;
}

async function SellCard(cardID, userID){
    fetch("/market/sell/"+cardID+'/'+userID, {
                                                method:'POST',
                                                headers: new Headers(),
                                            }
    )
    .then(response => {
        console.log("Success SellCard ");
        alert("Card sold succesfully");
        UpdateMoney(userID);
    })
    .catch(error => console.error(error));
}

// update money 

async function UpdateMoney(userID){
    var user = await GetUserByID(userID);
    document.getElementById("money").innerHTML = user.money;
}


// let cardList = [
//         {
//             family_name:"DC Comic",
//             img_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif",
//             name:"SUPERMAN",
//             description: "The origin story of Superman relates that he was born Kal-El on the planet Krypton, before being rocketed to Earth as an infant by his scientist father Jor-El, moments before Krypton's destruction. Discovered and adopted by a farm couple from Kansas, the child is raised as Clark Kent and imbued with a strong moral compass. Early in his childhood, he displays various superhuman abilities, which, upon reaching maturity, he resolves to use for the benefit of humanity through a 'Superman' identity.",
//             hp: 500,
//             energy:100,
//             attack:50,
//             defense: 50,
//             price:200
//         },
//         {
//             --family_name:"DC Comic", family_name
//             img_src:"https://static.fnac-static.com/multimedia/Images/8F/8F/7D/66/6716815-1505-1540-1/tsp20171122191008/Lego-lgtob12b-lego-batman-movie-lampe-torche-batman.jpg",
//             name:"BATMAN", card_name
//             description: "Bruce Wayne, alias Batman, est un héros de fiction appartenant à l'univers de DC Comics. Créé par le dessinateur Bob Kane et le scénariste Bill Finger, il apparaît pour la première fois dans le comic book Detective Comics no 27 (date de couverture : mai 1939 mais la date réelle de parution est le 30 mars 1939) sous le nom de The Bat-Man. Bien que ce soit le succès de Superman qui ait amené sa création, il se détache de ce modèle puisqu'il n'a aucun pouvoir surhumain. Batman n'est qu'un simple humain qui a décidé de lutter contre le crime après avoir vu ses parents se faire abattre par un voleur dans une ruelle de Gotham City, la ville où se déroulent la plupart de ses aventures. Malgré sa réputation de héros solitaire, il sait s'entourer d'alliés, comme Robin, son majordome Alfred Pennyworth ou encore le commissaire de police James Gordon. ",
//             -- cardDescriptionId
//             --hp: 50, cardHPId
//             --energy:80, energyId
//             attack:170, cardAttackId
//             defense: 80, cardDefenseId
//             price:100, cardPriceId
//         },
//         {
//             family_name:"Marvel",
//             img_src:"https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg",
//             name:"DEAD POOL",
//             description: "Le convoi d'Ajax est attaqué par Deadpool. Il commence par massacrer les gardes à l'intérieur d'une voiture, avant de s'en prendre au reste du convoi. Après une longue escarmouche, où il est contraint de n'utiliser que les douze balles qu'il lui reste, Deadpool capture Ajax (dont le véritable nom est Francis, ce que Deadpool ne cesse de lui rappeler). Après l'intervention de Colossus et Negasonic venus empêcher Deadpool de causer plus de dégâts et le rallier à la cause des X-Men, Ajax parvient à s'échapper en retirant le sabre de son épaule. Il apprend par la même occasion la véritable identité de Deadpool : Wade Wilson.",
//             hp: 999999,
//             energy:100,
//             attack:15,
//             defense: 15,
//             price:250
//         },
//     ]

let cardList=myUser.listCard;

let template = document.querySelector("#row");

async function DisplayCardList(buySell){

    // if (buySell == 'buy'){
    //     cardList = await GetCardsToBuy();
    // } else if (buySell == 'sell'){
    //     cardList = await GetCardsToSell();
    // } else { alert('buySell variable isnt buy or sell');}


    for(const card of cardList){
        let clone = document.importNode(template.content, true);
    
        newContent= clone.firstElementChild.innerHTML
                    .replace(/{{family_src}}/g, card.family_src)
                    .replace(/{{family_name}}/g, card.family_name)
                    .replace(/{{img_src}}/g, card.img_src)
                    .replace(/{{name}}/g, card.name)
                    .replace(/{{description}}/g, card.description)
                    .replace(/{{hp}}/g, card.hp)
                    .replace(/{{energy}}/g, card.energy)
                    .replace(/{{attack}}/g, card.attack)
                    .replace(/{{defense}}/g, card.defense)
                    .replace(/{{price}}/g, card.price)
                    .replace(/{{buySell}}/g, buySell);
        clone.firstElementChild.innerHTML= newContent;
    
        let cardContainer= document.querySelector("#tableContent");
        cardContainer.appendChild(clone);
    }
}


async function DisplayCard(td){

    // the original (the card in ./part/card-full.html)
    // await $("#card").DisplayCard();

    // // fetching 
    var cardName = td.children[1].innerHTML;
    console.log("cardName :",cardName);

    console.log("cardList :",cardList);
    var myCard = cardList.find(element => element.name==cardName);
    console.log("myCard :",myCard);
    // // document.getElementById("family_name").innerHTML=myCard.family_name;
    // console.log(document.querySelector("#card-display"));

    document.getElementById("family_name").innerHTML=myCard.family_name;
    document.getElementById("card_name").innerHTML=myCard.name;
    document.getElementById("energyId").innerHTML=myCard.energy;
    document.getElementById("cardAttackId").innerHTML=myCard.attack;
    document.getElementById("cardDefenceId").innerHTML=myCard.defense;
    document.getElementById("cardPriceId").innerHTML=myCard.price;
    document.getElementById("cardDescriptionId").innerHTML=myCard.description;
    document.getElementById("cardHPId").innerHTML=myCard.hp;
    document.getElementById("cardImgId").src=myCard.img_src;

//             --family_name:"DC Comic", family_name
//             img_src:"https://static.fnac-static.com/multimedia/Images/8F/8F/7D/66/6716815-1505-1540-1/tsp20171122191008/Lego-lgtob12b-lego-batman-movie-lampe-torche-batman.jpg",
//             name:"BATMAN", card_name
//             description: "Bruce Wayne, alias Batman, est un héros de fiction appartenant à l'univers de DC Comics. Créé par le dessinateur Bob Kane et le scénariste Bill Finger, il apparaît pour la première fois dans le comic book Detective Comics no 27 (date de couverture : mai 1939 mais la date réelle de parution est le 30 mars 1939) sous le nom de The Bat-Man. Bien que ce soit le succès de Superman qui ait amené sa création, il se détache de ce modèle puisqu'il n'a aucun pouvoir surhumain. Batman n'est qu'un simple humain qui a décidé de lutter contre le crime après avoir vu ses parents se faire abattre par un voleur dans une ruelle de Gotham City, la ville où se déroulent la plupart de ses aventures. Malgré sa réputation de héros solitaire, il sait s'entourer d'alliés, comme Robin, son majordome Alfred Pennyworth ou encore le commissaire de police James Gordon. ",
//             -- cardDescriptionId
//             --hp: 50, cardHPId
//             --energy:80, energyId
//             attack:170, cardAttackId
//             defense: 80, cardDefenseId
//             price:100, cardPriceId

    cardDiv.style.display="";



}

var cardBigDiv = document.getElementById("card-big-div");
cardBigDiv.style.visibility="hidden";
var cardDiv = document.getElementById("card");
cardDiv.style.display="none";

function DisplayShop(buySell){

    document.getElementById("choice").style.display="none";
    cardBigDiv.style.visibility="visible";

    DisplayCardList(buySell);


}





