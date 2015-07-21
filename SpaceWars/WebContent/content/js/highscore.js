$.getJSON(winner.json, function (data) {
    $.each(data.response.venue.tips.groups, function (index, value) {
        $.each(this.items, function () {
            console.log(this.text);
        });
    });
});