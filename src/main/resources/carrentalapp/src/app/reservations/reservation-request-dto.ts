export class ReservationRequestDto {
    //userId!: number;
    //OPCJONALNIE DO ZMIANY NA ID W SESJI ZAMIAST EMAILA
    email!: string | null;
    carId!: number;
    dateStart!: Date;
    dateEnd!: Date;
}
