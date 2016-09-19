download mysql: http://120.52.72.23/cdn.mysql.com/c3pr90ntc0td/Downloads/MySQL-5.5/mysql-5.5.52.tar.gz

1.install base software:

	yum -y install  gcc gcc-c++ gcc-g77 autoconf automake zlib* fiex* libxml* ncurses-devel libmcrypt* libtool-ltdl-devel* make

2.create user and group:

	groupadd mysql  
	useradd -g mysql -s /sbin/nologin -M mysql

3.use 'cmake' to set the compiler:

cmake . \
-DCMAKE_INSTALL_PREFIX=/usr/local/mysql \
-DMYSQL_DATADIR=/var/mysql/data \
-DSYSCONFDIR=/etc \
-DWITH_EMBEDDED_SERVER=0 \
-DWITH_MYISAM_STORAGE_ENGINE=1 \
-DWITH_INNOBASE_STORAGE_ENGINE=1 \
-DWITH_MEMORY_STORAGE_ENGINE=1 \
-DWITH_PARTITION_STORAGE_ENGINE=1 \
-DWITH_READLINE=1 \
-DMYSQL_UNIX_ADDR=/var/mysql/run/mysql-server.sock \
-DMYSQL_TCP_PORT=3306 \
-DENABLED_LOCAL_INFILE=1 \
-DEXTRA_CHARSETS=all \
-DDEFAULT_CHARSET=utf8 \
-DDEFAULT_COLLATION=utf8_general_ci
