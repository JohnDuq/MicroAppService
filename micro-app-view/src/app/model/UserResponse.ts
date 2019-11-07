import { Role } from './Role';
import { User } from './User';
import { Response } from './Response';
export class UserResponse extends Response {
    user: User;
    listRolesUser: Role[];
    listRolesAvaible: Role[];
}
