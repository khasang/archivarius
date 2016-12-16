$('input[type="checkbox"]').on('change', function() {

    // uncheck sibling checkboxes (checkboxes on the same row)
    $(this).siblings().prop('checked', false);

    // uncheck checkboxes in the same column
    $('div').find('input[type="checkbox"]:eq(' + $(this).index() + ')').not(this).prop('checked', false);

});