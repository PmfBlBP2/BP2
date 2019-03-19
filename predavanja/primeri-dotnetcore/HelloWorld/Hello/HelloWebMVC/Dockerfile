FROM microsoft/dotnet:2.1-aspnetcore-runtime AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM microsoft/dotnet:2.1-sdk AS build
WORKDIR /src
COPY ["HelloWebMVC/HelloWebMVC.csproj", "HelloWebMVC/"]
RUN dotnet restore "HelloWebMVC/HelloWebMVC.csproj"
COPY . .
WORKDIR "/src/HelloWebMVC"
RUN dotnet build "HelloWebMVC.csproj" -c Release -o /app

FROM build AS publish
RUN dotnet publish "HelloWebMVC.csproj" -c Release -o /app

FROM base AS final
WORKDIR /app
COPY --from=publish /app .
ENTRYPOINT ["dotnet", "HelloWebMVC.dll"]