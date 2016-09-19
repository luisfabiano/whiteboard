1.install base software

	yum -y install  gcc gcc-c++ gcc-g77 autoconf automake zlib* fiex* libxml* ncurses-devel libmcrypt* libtool-ltdl-devel* make

2.create user and group:

	groupadd mysql  
	useradd -g mysql -s /sbin/nologin -M mysql
