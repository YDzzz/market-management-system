create table if not exists PUBLIC.u_user(
    name varchar(255) primary key,
    u_password varchar(255) not null ,
    u_type enum('boss','storekeeper','salesman') not null
);

create table if not exists PUBLIC.inventory(
    id int primary key ,
    species varchar(255) not null unique ,
    discount decimal(10,2) not null ,
    price decimal(10,2) not null ,
    available int not null ,
    baseline int not null ,
    i_mode enum('缺货', '无货', '有货') not null
);

create table if not exists PUBLIC.saler(
    code int primary key ,
    id int not null ,
    amount int not null
);

create table if not exists PUBLIC.saleList(
    code int primary key ,
    s_time datetime not null ,
    saleId int not null ,
    Count decimal(10,2) not null
);

create table if not exists PUBLIC.purchase(
    purchaseId int primary key ,
    supplierId int not null ,
    batchNumber int not null ,
    species varchar(255) not null ,
    amount int not null ,
    Count decimal(10, 2) not null,
    state ENUM ('已入库', '退货中', '申请中', '已退货', '入库中') not null
);

create table if not exists PUBLIC.supplier(
    Id int primary key ,
    name varchar(255),
    s_type varchar(255)
);