
class App {

    static showSuspendedConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to delete Product ?',
            width: 400,
            padding: '0em',
            color: '#0e57f6',
            background: '#ebeede',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, Delete it !',
            cancelButtonText: 'Cancel',
        })
    }

    static showBlockConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to block user?',
            width: 400,
            padding: '0em',
            color: '#0e57f6',
            background: '#ebeede',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, Block now !',
            cancelButtonText: 'Cancel',
        })
    }

    static showUnLockConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to UnLock user?',
            width: 400,
            padding: '0em',
            color: '#0e57f6',
            background: '#ebeede',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, UnLock now !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        iziToast.warning({
            icon: 'warning',
            position: 'topRight',
            backgroundColor : "green",
             timeout: 2200,
            // title: 'Warning',
            message: t,
        })
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2100,
                message: t
            });
        }

        static showErrorAlert(t) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                background : "yellow",
                timeout: 1700,
                message: t
            });
        }
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
        //
        // ${#numbers.formatDecimal(num,3,2,'COMMA')}
        // ${#numbers.arrayFormatDecimal(numArray,3,2,'COMMA')}
        // ${#numbers.listFormatDecimal(numList,3,2,'COMMA')}
        // ${#numbers.setFormatDecimal(numSet,3,2,'COMMA')}
    }

    // static formatNumberSpace(x) {
    //     return x.toString.replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    // }

    // static removeFormatNumberSpace(x) {
    //     return x.toString().replace(" ", "");
    // }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}

class User {
    constructor(id, fullName, username, password, phone, address,role, deleted) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role ;
        this.deleted = deleted;
    }
}

class Role {
    constructor(id,code,name) {
        this.id = id;
        this.code = code;
        this.name = name ;
    }
}


    class Product {
    constructor(id, name, image, price, quantity, category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}

class Category{
    constructor(id, name) {
        this.id = id;
        this.name =name;
    }
}