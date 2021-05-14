const DB = low(new LocalStorage('db'));

DB.defaults({
    'shoppingCart' : [],
}).write();

function lsDB(dbName){
    this.dbName = dbName;

    this.insert = function(data) {
        data.id = btoa(Date.now().toString(26) + Math.random().toString(36).substring(7) + this.dbName);
        DB.get(this.dbName).push(data).write();
    }

    this.get = function() {
        return DB.get(this.dbName).value();
    }

    this.where = function(conditions) {
        return DB.get(this.dbName).find(conditions).value();
    }

    this.whereArr = function(conditions) {
        return DB.get(this.dbName).filter(conditions).value();
    }

    this.isExist = function(conditions){
        let dataGet = DB.get(this.dbName).find(conditions).value();
        if(typeof dataGet == 'undefined'){
            return false;
        }
        return true;
    }

    this.update = function(conditions,values) {
        DB.get(this.dbName).find(conditions).assign(values).write();
    }

    this.remove = function(conditions) {
        DB.get(this.dbName).remove(conditions).write();
    }

    this.removeAll = function() {
        DB.set(this.dbName, []).write();
    }
}

let shoppingCart = new lsDB('shoppingCart');