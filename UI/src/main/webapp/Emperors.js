function start() {
    $.getJSON('http://localhost:8080/Shop/rest/test/', function (data) {
        var items = document.getElementById("items");

        $.each(data, function (key, value) {
            var newContent;

            var newArticle = document.createElement("article");
            newArticle.className = "z1";

            var newH2 = document.createElement("h2");
            newContent = document.createTextNode(value.name);
            newH2.appendChild(newContent);

            var newDesc = document.createElement("desc");
            newContent = document.createTextNode(value.description);
            newDesc.appendChild(newContent);

            var newPrice = document.createElement("price");
            newContent = document.createTextNode(value.price);
            newPrice.appendChild(newContent);

            newArticle.appendChild(newH2);
            newArticle.appendChild(newDesc);
            newArticle.appendChild(newPrice);


            items.appendChild(newArticle);
        });
    });
}