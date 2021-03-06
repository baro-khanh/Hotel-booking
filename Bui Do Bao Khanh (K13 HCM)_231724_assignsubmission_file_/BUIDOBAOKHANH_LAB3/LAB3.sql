USE [master]
GO
/****** Object:  Database [LAB3]    Script Date: 12/10/2019 10:53:21 AM ******/
CREATE DATABASE [LAB3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LAB03', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\LAB03.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LAB03_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\LAB03_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LAB3] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LAB3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LAB3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LAB3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LAB3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LAB3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LAB3] SET ARITHABORT OFF 
GO
ALTER DATABASE [LAB3] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LAB3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LAB3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LAB3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LAB3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LAB3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LAB3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LAB3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LAB3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LAB3] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LAB3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LAB3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LAB3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LAB3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LAB3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LAB3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LAB3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LAB3] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LAB3] SET  MULTI_USER 
GO
ALTER DATABASE [LAB3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LAB3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LAB3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LAB3] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LAB3] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LAB3] SET QUERY_STORE = OFF
GO
USE [LAB3]
GO
/****** Object:  Table [dbo].[StatusOrder]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StatusOrder](
	[StatusID] [int] IDENTITY(1,1) NOT NULL,
	[StatusName] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_StatusOrder] PRIMARY KEY CLUSTERED 
(
	[StatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblAccount]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccount](
	[Email] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](12) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Status] [bit] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[Password] [nvarchar](max) NOT NULL,
	[RoleID] [int] NOT NULL,
 CONSTRAINT [PK_tblAccount] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblArea]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblArea](
	[AreaID] [int] IDENTITY(1,1) NOT NULL,
	[AreaName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tbl_Area] PRIMARY KEY CLUSTERED 
(
	[AreaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[DiscountCode] [nvarchar](50) NOT NULL,
	[Decrease] [int] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
	[Description] [nvarchar](50) NULL,
	[Status] [bit] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblFeedback]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFeedback](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[HotelID] [int] NOT NULL,
	[Feedback] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_tblFeedback] PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblHotel]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblHotel](
	[HotelID] [int] IDENTITY(1,1) NOT NULL,
	[HotelName] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](500) NOT NULL,
	[Phone] [nvarchar](12) NULL,
	[Photo] [nvarchar](max) NOT NULL,
	[AreaID] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[Description] [nvarchar](50) NULL,
	[CreateDate] [date] NOT NULL,
 CONSTRAINT [PK_tblHotel] PRIMARY KEY CLUSTERED 
(
	[HotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[DiscountCode] [nvarchar](50) NULL,
	[CreateDate] [date] NOT NULL,
	[Total] [float] NOT NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[RoomID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[CheckIn] [date] NOT NULL,
	[CheckOut] [date] NOT NULL,
	[SubPrice] [float] NOT NULL,
	[Status] [int] NOT NULL,
	[HotelID] [int] NOT NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoom](
	[RoomID] [int] IDENTITY(1,1) NOT NULL,
	[TypeRoomID] [int] NOT NULL,
	[HotelID] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[Description] [nvarchar](500) NULL,
	[RoomQuantity] [int] NOT NULL,
	[RoomName] [nvarchar](50) NULL,
	[Photo] [nvarchar](500) NULL,
 CONSTRAINT [PK_tblRoom] PRIMARY KEY CLUSTERED 
(
	[RoomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTypeRoom]    Script Date: 12/10/2019 10:53:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTypeRoom](
	[TypeRoomID] [int] IDENTITY(1,1) NOT NULL,
	[TypeName] [nvarchar](50) NOT NULL,
	[Tankage] [int] NOT NULL,
	[Price] [float] NOT NULL,
 CONSTRAINT [PK_tblTypeRoom] PRIMARY KEY CLUSTERED 
(
	[TypeRoomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[StatusOrder] ON 

INSERT [dbo].[StatusOrder] ([StatusID], [StatusName]) VALUES (1, N'active')
INSERT [dbo].[StatusOrder] ([StatusID], [StatusName]) VALUES (2, N'nonactive')
SET IDENTITY_INSERT [dbo].[StatusOrder] OFF
INSERT [dbo].[tblAccount] ([Email], [Phone], [Fullname], [Status], [CreateDate], [Password], [RoleID]) VALUES (N'', N'', N'', 1, CAST(N'2019-12-09' AS Date), N'', 2)
INSERT [dbo].[tblAccount] ([Email], [Phone], [Fullname], [Status], [CreateDate], [Password], [RoleID]) VALUES (N'100@gmail.com', N'012-1234123', N'baokhanh', 1, CAST(N'2019-12-09' AS Date), N'11111111', 2)
INSERT [dbo].[tblAccount] ([Email], [Phone], [Fullname], [Status], [CreateDate], [Password], [RoleID]) VALUES (N'dungbeo@gmail.com', N'123-3456789', N'dung', 1, CAST(N'2019-12-03' AS Date), N'12345678', 2)
INSERT [dbo].[tblAccount] ([Email], [Phone], [Fullname], [Status], [CreateDate], [Password], [RoleID]) VALUES (N'khanh@gmail.com', N'012-3456789', N'khanh', 1, CAST(N'2019-12-03' AS Date), N'12345678', 2)
SET IDENTITY_INSERT [dbo].[tblArea] ON 

INSERT [dbo].[tblArea] ([AreaID], [AreaName]) VALUES (1, N'Dalat')
INSERT [dbo].[tblArea] ([AreaID], [AreaName]) VALUES (2, N'Hanoi')
INSERT [dbo].[tblArea] ([AreaID], [AreaName]) VALUES (3, N'TpHcm')
SET IDENTITY_INSERT [dbo].[tblArea] OFF
INSERT [dbo].[tblDiscount] ([DiscountCode], [Decrease], [StartDate], [EndDate], [Description], [Status]) VALUES (N'WELCOME1', 10, CAST(N'2019-12-05' AS Date), CAST(N'2019-12-06' AS Date), NULL, 1)
INSERT [dbo].[tblDiscount] ([DiscountCode], [Decrease], [StartDate], [EndDate], [Description], [Status]) VALUES (N'WELCOME2', 10, CAST(N'2019-12-05' AS Date), CAST(N'2019-12-10' AS Date), NULL, 0)
INSERT [dbo].[tblDiscount] ([DiscountCode], [Decrease], [StartDate], [EndDate], [Description], [Status]) VALUES (N'WELCOME3', 10, CAST(N'2019-12-05' AS Date), CAST(N'2019-12-09' AS Date), NULL, 1)
INSERT [dbo].[tblDiscount] ([DiscountCode], [Decrease], [StartDate], [EndDate], [Description], [Status]) VALUES (N'WELCOME4', 50, CAST(N'2019-12-09' AS Date), CAST(N'2019-12-15' AS Date), NULL, 1)
SET IDENTITY_INSERT [dbo].[tblFeedback] ON 

INSERT [dbo].[tblFeedback] ([FeedbackID], [Email], [HotelID], [Feedback]) VALUES (3, N'khanh@gmail.com', 2, N'ok')
INSERT [dbo].[tblFeedback] ([FeedbackID], [Email], [HotelID], [Feedback]) VALUES (5, N'khanh@gmail.com', 2, N'ok')
INSERT [dbo].[tblFeedback] ([FeedbackID], [Email], [HotelID], [Feedback]) VALUES (6, N'khanh@gmail.com', 2, N'good')
INSERT [dbo].[tblFeedback] ([FeedbackID], [Email], [HotelID], [Feedback]) VALUES (7, N'khanh@gmail.com', 2, N'badddd')
SET IDENTITY_INSERT [dbo].[tblFeedback] OFF
SET IDENTITY_INSERT [dbo].[tblHotel] ON 

INSERT [dbo].[tblHotel] ([HotelID], [HotelName], [Address], [Phone], [Photo], [AreaID], [Status], [Description], [CreateDate]) VALUES (2, N'Marina Bay Sand', N'10 Bayfront Avenue, Marina Bay', N'018956', N'59641250_354048005238120_5179124667939553280_n.jpg', 1, 1, NULL, CAST(N'2019-12-03' AS Date))
INSERT [dbo].[tblHotel] ([HotelID], [HotelName], [Address], [Phone], [Photo], [AreaID], [Status], [Description], [CreateDate]) VALUES (3, N'Six Senses Duxton', N'83 Duxton Road, Chinatown', N'089540', N'73316450_2606047629481674_505413106940248064_o.jpg', 2, 1, NULL, CAST(N'2019-12-03' AS Date))
INSERT [dbo].[tblHotel] ([HotelID], [HotelName], [Address], [Phone], [Photo], [AreaID], [Status], [Description], [CreateDate]) VALUES (4, N'Novotel Clarke Quay', N'177A River Valley Road, Clarke Quay', N'179031', N'48317936_2057337604352458_5007429888538312704_o.jpg', 3, 1, NULL, CAST(N'2019-12-03' AS Date))
INSERT [dbo].[tblHotel] ([HotelID], [HotelName], [Address], [Phone], [Photo], [AreaID], [Status], [Description], [CreateDate]) VALUES (5, N'Aerotel Transit Hotel', N'evel 3, Departure Transit Zone, Terminal 1 (near Gate D41)', N'819642', N'61658262_365482880761299_2123885126285787136_n.jpg', 3, 1, NULL, CAST(N'2019-12-03' AS Date))
SET IDENTITY_INSERT [dbo].[tblHotel] OFF
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (1, N'khanh@gmail.com', NULL, CAST(N'2019-06-12' AS Date), 12)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (2, N'khanh@gmail.com', NULL, CAST(N'2019-01-12' AS Date), 55)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (5, N'khanh@gmail.com', NULL, CAST(N'2019-12-12' AS Date), 0)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (7, N'khanh@gmail.com', NULL, CAST(N'2019-12-08' AS Date), 50)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (8, N'khanh@gmail.com', NULL, CAST(N'2019-12-08' AS Date), 300)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (9, N'khanh@gmail.com', NULL, CAST(N'2019-12-08' AS Date), 200)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (10, N'khanh@gmail.com', N'WELCOME3', CAST(N'2019-12-08' AS Date), 45)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (11, N'khanh@gmail.com', N'WELCOME4', CAST(N'2019-12-09' AS Date), 72)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (12, N'khanh@gmail.com', NULL, CAST(N'2019-12-09' AS Date), 160)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (13, N'khanh@gmail.com', NULL, CAST(N'2019-12-09' AS Date), 30)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (14, N'khanh@gmail.com', NULL, CAST(N'2019-12-09' AS Date), 30)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (15, N'khanh@gmail.com', NULL, CAST(N'2019-12-09' AS Date), 80)
INSERT [dbo].[tblOrder] ([OrderID], [Email], [DiscountCode], [CreateDate], [Total]) VALUES (16, N'khanh@gmail.com', N'WELCOME4', CAST(N'2019-12-10' AS Date), 80)
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (1, 1, 8, 4, CAST(N'2019-12-10' AS Date), CAST(N'2019-12-15' AS Date), 5, 1, 3)
INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (2, 7, 5, 2, CAST(N'2019-12-10' AS Date), CAST(N'2019-12-15' AS Date), 50, 1, 2)
INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (3, 8, 6, 2, CAST(N'2019-12-10' AS Date), CAST(N'2019-12-15' AS Date), 50, 1, 2)
INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (4, 9, 9, 3, CAST(N'2019-12-08' AS Date), CAST(N'2019-12-10' AS Date), 200, 1, 4)
INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (5, 10, 14, 4, CAST(N'2019-12-08' AS Date), CAST(N'2019-12-09' AS Date), 50, 1, 4)
INSERT [dbo].[tblOrderDetail] ([OrderDetailID], [OrderID], [RoomID], [Quantity], [CheckIn], [CheckOut], [SubPrice], [Status], [HotelID]) VALUES (13, 16, 7, 2, CAST(N'2019-12-10' AS Date), CAST(N'2019-12-11' AS Date), 160, 1, 2)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
SET IDENTITY_INSERT [dbo].[tblRole] ON 

INSERT [dbo].[tblRole] ([RoleID], [RoleName]) VALUES (1, N'admin')
INSERT [dbo].[tblRole] ([RoleID], [RoleName]) VALUES (2, N'customer')
SET IDENTITY_INSERT [dbo].[tblRole] OFF
SET IDENTITY_INSERT [dbo].[tblRoom] ON 

INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (5, 2, 2, 1, N'TV, Cable TV, LCD TV, Internet access (complimentary), TV channels, Phone', 2, N'DELUXE LAKE VIEW', N'47684049_2057336204352598_1819955231622955008_o.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (6, 2, 2, 1, N'TV, Cable TV, Internet access (free)', 5, N' PREMIUM SUITE', N'35923072_894289160770268_3559370128409755648_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (7, 3, 2, 1, N'Internet access (free)', 6, N' FAMILY SUITE', N'50031917_792427614429397_7294840929637105664_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (8, 1, 3, 1, N'TV, Cable TV, Internet access (complimentary), TV channels, Phone', 4, N'Deluxe Pine Hill View', N'47580227_1306804776151807_2541603032630558720_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (9, 2, 4, 1, N'TV, Cable TV, LCD TV, Internet access (complimentary), TV channels, Phone', 5, N'Luxury Garden View', N'47579558_1306806076151677_3406462545278533632_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (10, 3, 4, 1, N'Internet access (free)', 4, N'Luxury Partial Lake View', N'53160405_973432522845741_4496789707614584832_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (12, 1, 4, 1, N'TV, Cable TV, LCD TV, Internet access (complimentary), TV channels, Phone', 4, N'Junior Suite Garden View', N'59295190_10215677055213119_1225385720378556416_n.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (13, 2, 4, 1, N'TV, Cable TV, LCD TV, Internet access (complimentary), TV channels, Phone', 3, N'Junior Suite Lake View', N'72165446_971626159841052_3928202510833025024_o.jpg')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (14, 2, 4, 1, N'TV, Cable TV, LCD TV, Internet access (complimentary), TV channels, Phone', 3, N'Eden Suite Garden View', N'jj.PNG')
INSERT [dbo].[tblRoom] ([RoomID], [TypeRoomID], [HotelID], [Status], [Description], [RoomQuantity], [RoomName], [Photo]) VALUES (15, 3, 5, 1, NULL, 3, NULL, N'73390678_556954708203509_8902742115344187392_n.jpg')
SET IDENTITY_INSERT [dbo].[tblRoom] OFF
SET IDENTITY_INSERT [dbo].[tblTypeRoom] ON 

INSERT [dbo].[tblTypeRoom] ([TypeRoomID], [TypeName], [Tankage], [Price]) VALUES (1, N'Single', 1, 30)
INSERT [dbo].[tblTypeRoom] ([TypeRoomID], [TypeName], [Tankage], [Price]) VALUES (2, N'Couple', 2, 50)
INSERT [dbo].[tblTypeRoom] ([TypeRoomID], [TypeName], [Tankage], [Price]) VALUES (3, N'Family', 7, 80)
SET IDENTITY_INSERT [dbo].[tblTypeRoom] OFF
ALTER TABLE [dbo].[tblAccount]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblRole] FOREIGN KEY([RoleID])
REFERENCES [dbo].[tblRole] ([RoleID])
GO
ALTER TABLE [dbo].[tblAccount] CHECK CONSTRAINT [FK_tblAccount_tblRole]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblAccount] FOREIGN KEY([Email])
REFERENCES [dbo].[tblAccount] ([Email])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblAccount]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblHotel] FOREIGN KEY([HotelID])
REFERENCES [dbo].[tblHotel] ([HotelID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblHotel]
GO
ALTER TABLE [dbo].[tblHotel]  WITH CHECK ADD  CONSTRAINT [FK_tblHotel_tbl_Area] FOREIGN KEY([AreaID])
REFERENCES [dbo].[tblArea] ([AreaID])
GO
ALTER TABLE [dbo].[tblHotel] CHECK CONSTRAINT [FK_tblHotel_tbl_Area]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblAccount] FOREIGN KEY([Email])
REFERENCES [dbo].[tblAccount] ([Email])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblAccount]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblDiscount] FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[tblDiscount] ([DiscountCode])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblDiscount]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_StatusOrder] FOREIGN KEY([Status])
REFERENCES [dbo].[StatusOrder] ([StatusID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_StatusOrder]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([OrderID])
REFERENCES [dbo].[tblOrder] ([OrderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblRoom] FOREIGN KEY([RoomID])
REFERENCES [dbo].[tblRoom] ([RoomID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblRoom]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblHotel] FOREIGN KEY([HotelID])
REFERENCES [dbo].[tblHotel] ([HotelID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblHotel]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblTypeRoom] FOREIGN KEY([TypeRoomID])
REFERENCES [dbo].[tblTypeRoom] ([TypeRoomID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblTypeRoom]
GO
USE [master]
GO
ALTER DATABASE [LAB3] SET  READ_WRITE 
GO
