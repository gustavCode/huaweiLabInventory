
$('document').ready(function () {

    // creating the edit modal and get the values in each field
    $('.card .edit-btn').on('click', function (event) {

        event.preventDefault();

        var myModal = new bootstrap.Modal(document.getElementById('editDeviceModal'));

        var href = $(this).attr('href');

        $.get(href, function (device, status) {
            $('#edit_device_id').val(device.deviceID);
            $('#edit_device_name').val(device.deviceName);
            $('#edit_device_type').val(device.deviceType);
            $('#edit_device_description').val(device.deviceDescription);
            $('#edit_device_quantity').val(device.deviceQuantity);
        });

        myModal.show();
    })

});

// $('document').ready(function () {
//
//     // creating the request modal and getting its value
//     $('.card .request_btn').on('click', function (event) {
//
//         event.preventDefault();
//
//         var requestModal = new bootstrap.Modal(document.getElementById('requestDevice'));
//
//         requestModal.show();
//     });
// });